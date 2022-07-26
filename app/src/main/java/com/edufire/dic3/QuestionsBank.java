package com.edufire.dic3;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    public static List<QuestionsList> getAllQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final QuestionsList question1 = new QuestionsList("Enliven means the opposite of…", "A. Depress", "B. Imagine", "C. Accost", "D. Refrain", "A. Depress", "") ;
        final QuestionsList question2 = new QuestionsList("Secede means the opposite of…", "A. Accessible", "B. Recall", "C. Merge", "D. Bail", "C. Merge", "") ;
        final QuestionsList question3 = new QuestionsList("Coarse most nearly means…", "A. Soft", "B. Polite", "C. Harsh", "D. Direction", "C. Harsh", "") ;
        final QuestionsList question4 = new QuestionsList("Alleviate means the opposite of…", "A. Hover", "B. Worsen", "C. Intend", "D. Large", "B. Worsen", "") ;
        final QuestionsList question5 = new QuestionsList("All of the following are indefinite pronouns except", "A. Both", "B. No one", "C. Neither", "D. It", "D. It", "") ;
        final QuestionsList question6 = new QuestionsList("Which of the following words is an adverb?", "A. Lovely", "B. Lonely", "C. Slowly", "D. Friendly", "C. Slowly", "") ;

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }
}
