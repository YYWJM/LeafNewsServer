package com.yuanyin.leafnews.modules.semanticProcess.service.impl;

import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
import com.yuanyin.leafnews.modules.semanticProcess.entity.KeyWord;
import com.yuanyin.leafnews.modules.semanticProcess.entity.Word;
import com.yuanyin.leafnews.modules.semanticProcess.service.CoreNLPService;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by yuanyin on 16/5/24.
 */
@Service
public class CoreNPLServiceImpl implements CoreNLPService {
    @Resource
    private NewsService newsService;

    /**
     * 给所有的新闻信息提取关键词
     */
    @Override
    public void setAllKeyWord() {
        List<News> newsList = newsService.getAll();
        for (News news : newsList) {
            setKeyWord(news);
        }
    }

    /**
     * 对一条新闻信息提取关键词
     *
     * @param news 新闻信息
     */
    public void setKeyWord(News news) {
        //关键词备选列表
        List<Word> wordList = new ArrayList<>();
        //最终的关键词列表
        List<KeyWord> keyWordList = new ArrayList<>();

        //创建一个stanfordcorenlp对象，提供词性标注、词干提取，NER，解析，和指代消解分析
        Properties props = new Properties();
        props.put("annotators", "tokenize, cleanxml, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        //将新闻内容交给CoreNLP进行分析
        Annotation document = new Annotation(news.getBody());
        pipeline.annotate(document);

        //获得新闻文本中的所有句子
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            //获得句子中的所有单词
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                //获得单词的词性
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                //如果词性为名词或名词的变化,则对其进行分析,否则放弃分析
                if (pos.length() < 2 || !pos.substring(0, 2).equals("NN"))
                    continue;
                //新建Word对象,将其作为关键词备选对象
                Word word = new Word();
                //设置单词内容
                word.setOriginal(token.get(CoreAnnotations.TextAnnotation.class));
                //设置单词词性
                word.setPos(pos);
                //设置单词词性
                word.setLemma(token.get(CoreAnnotations.LemmaAnnotation.class).toLowerCase());
                //检测该单词是否在备选列表中,若两词词根相同则认为两词相同
                int index = wordList.indexOf(word);
                if (index == -1) {
                    //如果该单词不在备选列表中,则加入备选列表
                    wordList.add(word);
                } else {
                    //如果该单词已经在备选列表中,则将该单词的出现次数加一
                    wordList.get(index).addNumber();
                }
            }
        }

        //对关键词备选列表中的单词按出现次数从高到底排序
        Collections.sort(wordList);
        //将出现次数最多的五个单词加入关键词列表,并计算出关键词出现的总次数
        int sumNumber = 0;
        for (Word word : wordList.subList(0, 5)) {
            KeyWord keyWord = new KeyWord();
            keyWord.setContent(word.getOriginal());
            keyWord.setWeight(word.getNumber());
            keyWordList.add(keyWord);
            sumNumber += word.getNumber();
        }
        //设置关键词权重,即该关键词出现的次数占次数的百分比,保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        for (KeyWord keyWord : keyWordList) {
            keyWord.setWeight(Double.parseDouble(df.format(keyWord.getWeight() / sumNumber)));
        }
        //保存新闻信息的关键词数组
        news.setKeyWord(keyWordList.toString());
        newsService.updateByPrimaryKey(news);
    }

}
