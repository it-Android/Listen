package com.admin.listen.analysis;


import com.admin.listen.analysis.entity.DetailedData;
import com.admin.listen.analysis.entity.GradeAllData;
import com.admin.listen.analysis.entity.UnitData;
import com.admin.listen.analysis.entity.WordData;
import com.admin.listen.database.Word;
import com.admin.listen.database.WordDao;
import com.admin.listen.utils.DbUtil;
import com.admin.listen.utils.OkUtil;
import com.admin.listen.utils.SyUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/28 16:26
 **/
public class ExtractOkhttp {
    private ExecutorService executorService;

    /**
     * 等级解析（小学、初中、高中）
     *
     * @param paramer
     * @return
     */
    private GradeAllData analysisGrade(String paramer) {
        try {
            String html = OkUtil.init().okGet(paramer).execute().body().string();
            GradeAnalysis analysis = new GradeAnalysis();
            return analysis.analysis(html);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 书的章节（单词数量）
     *
     * @param paramer
     * @return
     */
    private UnitData analysisUnit(String paramer) {
        try {
            String html = OkUtil.init().okGet(paramer).execute().body().string();
            UnitAnalysis analysis = new UnitAnalysis();
            return analysis.analysis(html);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 章节的单词预览
     *
     * @param paramer
     * @return
     */
    private List<WordData> analysisWord(String paramer) {
        try {
            String html = OkUtil.init().okGet(paramer).execute().body().string();
            WordAnalysis analysis = new WordAnalysis();
            return analysis.analysis(html);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 单词详细信息
     *
     * @param paramer
     * @return
     */
    private DetailedData analysisDetailed(String paramer) {
        try {
            String html = OkUtil.init().okGet(paramer).execute().body().string();
            DetailedAnalysis analysis = new DetailedAnalysis();
            return analysis.analysis(html);
        } catch (IOException e) {
            return null;
        }
    }

    public void downUnitToWord(String paramer, boolean isSave, AnalysisCallBack analysisCallBack) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(5);
        }
        String dbName = SyUtils.strToMD5(paramer);
        executorService.execute(() -> {
            UnitData unitData = analysisUnit(paramer);
            if(unitData==null)return;
            List<UnitData.OneUnit> listUnit = unitData.getListUnit();
            int number = listUnit.size();
            AtomicInteger progress = new AtomicInteger();
            progress.set(0);
            if (analysisCallBack != null) {
                analysisCallBack.onStartAnalysis(number);
            }
            List<WordData> allData = new Vector<>();
            for (UnitData.OneUnit oneUnit : listUnit) {
                executorService.execute(() -> {
                    List<WordData> wordData = analysisWord(oneUnit.getPageUrl());
                    if(wordData==null)return;
                    allData.addAll(wordData);
                    int pro = progress.getAndIncrement();
                    if (analysisCallBack != null) {
                        analysisCallBack.onProgressAnalysis(pro);
                        if (pro >= number - 1) {
                            analysisCallBack.onCompleteAnalysis(allData);
                            return;
                        }
                    }
                    if (isSave) {
                        DbUtil.init().getDatabase(dbName).getWordDao().inserts(wdsToWords(wordData));
                    }
                });
            }
        });
    }


    public void downUnitToDetailed(String paramer, AnalysisCallBack analysisCallBack) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(5);
        }
        executorService.execute(() -> {
            String dbName = SyUtils.strToMD5(paramer);
            downUnitToWord(paramer, true, new AnalysisCallBack() {
                @Override
                public void onStartAnalysis(int num) {

                }

                @Override
                public void onProgressAnalysis(int progress) {

                }

                @Override
                public void onCompleteAnalysis(Object obj) {
                    List<WordData> wordData = (List<WordData>) obj;
                    saveData(dbName, wordData, analysisCallBack);
                }
            });
        });
    }

    public void saveData(String dbName, List<WordData> wordData, AnalysisCallBack analysisCallBack) {
        AtomicInteger progress = new AtomicInteger();
        WordDao wordDao = DbUtil.init().getDatabase(dbName).getWordDao();
        int number = wordData.size();
        if (analysisCallBack != null) {
            analysisCallBack.onStartAnalysis(number);
        }
        for (WordData data : wordData) {
            Word words = wordDao.getWord(data.getEnglish());
            if (words != null) {
                if (words.getSkill() != null || words.getListExample() != null) {
                    int pr=progress.getAndIncrement();
                    if (analysisCallBack != null) {
                        if (pr < number-1) {
                            analysisCallBack.onProgressAnalysis(pr);
                        } else {
                            analysisCallBack.onCompleteAnalysis(null);
                        }
                    }
                    //详细信息完整则跳过
                    continue;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //开始提取详细信息
            executorService.execute(() -> {
                DetailedData detailedData = analysisDetailed(data.getDetailedUrl());
                if (detailedData != null) {
                    wordDao.updata(ddToWord(detailedData));
                    int pr = progress.getAndIncrement();
                    if (analysisCallBack != null) {
                        if (pr < number-1) {
                            analysisCallBack.onProgressAnalysis(pr);
                        } else {
                            analysisCallBack.onCompleteAnalysis(null);
                        }
                    }
                }

            });
        }
    }

    public static Word wdToWord(WordData data) {
        Word word = new Word();
        word.setEnglish(data.getEnglish());//英语
        word.setChinese(data.getChinese());//中文
        word.setPhoneticSymbol(data.getPhoneticSymbol());//音标
        word.setPronunciation(data.getPronunciation());//发音
        return word;
    }

    public static Word ddToWord(DetailedData data) {
        Word word = new Word();
        word.setEnglish(data.getEnglish());//英语
        word.setChinese(data.getChinese());//中文
        word.setPhoneticSymbol(data.getPhoneticSymbol());//音标
        word.setPronunciation(data.getPronunciation());//发音
        word.setSkill(data.getSkill());
        Gson gson = new Gson();
        word.setListExample(gson.toJson(data.getList()));
        return word;
    }

    public static List<Word> wdsToWords(List<WordData> wordData) {
        List<Word> list = new ArrayList<>();
        for (WordData data : wordData) {
            list.add(wdToWord(data));
        }
        return list;
    }

    public interface AnalysisCallBack {
        void onStartAnalysis(int num);

        void onProgressAnalysis(int progress);

        void onCompleteAnalysis(Object obj);
    }
}
