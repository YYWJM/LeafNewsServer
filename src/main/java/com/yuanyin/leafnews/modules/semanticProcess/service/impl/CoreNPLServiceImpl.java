package com.yuanyin.leafnews.modules.semanticProcess.service.impl;

import com.yuanyin.leafnews.modules.semanticProcess.entity.KeyWord;
import com.yuanyin.leafnews.modules.semanticProcess.entity.Word;
import com.yuanyin.leafnews.modules.semanticProcess.service.CoreNLPService;
import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
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

    public void setKeyWord(News news) {
        List<Word> wordList = new ArrayList<>();
        List<KeyWord> keyWordList = new ArrayList<>();

        Properties props = new Properties();
        props.put("annotators", "tokenize, cleanxml, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(news.getBody());
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                Word word = new Word();
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.length() < 2 || !pos.substring(0, 2).equals("NN"))
                    continue;
                word.setOriginal(token.get(CoreAnnotations.TextAnnotation.class));
                word.setPos(pos);
                word.setLemma(token.get(CoreAnnotations.LemmaAnnotation.class).toLowerCase());
                int index = wordList.indexOf(word);
                if (index == -1) {
                    wordList.add(word);
                } else {
                    wordList.get(index).addNumber();
                }
            }
        }

        Collections.sort(wordList);
        int sumNumber = 0;
        for (Word word : wordList.subList(0, 5)) {
            KeyWord keyWord = new KeyWord();
            keyWord.setContent(word.getOriginal());
            keyWord.setWeight(word.getNumber());
            keyWordList.add(keyWord);
            sumNumber += word.getNumber();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        for (KeyWord keyWord : keyWordList) {
            keyWord.setWeight(Double.parseDouble(df.format(keyWord.getWeight() / sumNumber)));
        }

        news.setKeyWord(keyWordList.toString());
        newsService.updateByPrimaryKey(news);
    }

    @Override
    public void setAllKeyWord() {
        List<News> newsList = newsService.getAll();
        for (News news : newsList) {
            setKeyWord(news);
        }
    }
}
