package com.edufire.dic3;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    public static List<QuestionsList> getAllQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final QuestionsList question1 = new QuestionsList("What is the synonym", "A. ", "B. ", "C. ", "D. ", "A. ", "") ;
        final QuestionsList question2 = new QuestionsList("What is the synonym", "A. ", "B. ", "C. ", "D. ", "A. ", "") ;
        final QuestionsList question3 = new QuestionsList("What is the synonym", "A. ", "B. ", "C. ", "D. ", "A. ", "") ;
        final QuestionsList question4 = new QuestionsList("What is the synonym", "A. ", "B. ", "C. ", "D. ", "A. ", "") ;
        final QuestionsList question5 = new QuestionsList("What is the synonym", "A. ", "B. ", "C. ", "D. ", "A. ", "") ;
        final QuestionsList question6 = new QuestionsList("What is the synonym", "A. ", "B. ", "C. ", "D. ", "A. ", "") ;

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }
}
