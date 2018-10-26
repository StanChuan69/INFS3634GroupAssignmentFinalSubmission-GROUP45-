package com.rui.guang.infs3634groupassignment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rui.guang.infs3634groupassignment.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuestionsTable.COLUMN_EXPLANATION + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("What differentiates abstraction and encapsulation?", "Abstraction binds and encapsulation hides.", "Abstraction hides and encapsulation binds.", "Either can be used any way.","Abstraction hides and encapsulation hides.", 2, "Answer 2 - Abstraction hides the implementation details, whereas encapsulation binds the data, i.e. it combines the data and functions together.");
        addQuestion(q1);
        Question q2 = new Question("What is a subclass?", "A subclass is a class that extends another class.", "A subclass is a class declared inside a class.", "Both of the above.","None of the above.", 1, "Answer 1 - A subclass is a class that extends another class, i.e. it inherits the functionality of the class it extends.");
        addQuestion(q2);
        Question q3 = new Question("Overloading a method with different sets of parameters is known as ______ polymorphism.", "Static", "Compile time", "Both", "Neither ", 2, "Answer 2 - At compile time the Java Virtual Machine will decide which method to execute depending on the paramters.");
        addQuestion(q3);
        Question q4 = new Question("Which of the following statement is not true.", "Encapsulation enhances the maintainability of the code.", "Encapsulation allows for changes to the internal design of a class while the public interface remains unchanged.", "A well-encapsulated class defines its instance variables as private and allows access to these variables using methods.","Encapsulation defines a class without defining the implementation.", 4, "Bad luck!");
        addQuestion(q4);
        Question q5 = new Question("Which of the following best defines abstraction?", "Hiding the implementation.", "Showing the important data.", "Hiding the important data.","Hiding the implementation and showing only the features.", 4, "Answer 4 Abstraction is required to hide the implementation complexity and details from users, while only showing the required data fields and features.");
        addQuestion(q5);
        Question q6 = new Question("Two or more methods with the same name in the same class with different parameters is called as:", "Method overriding", "Method overloading", "Either", "Neither", 2, "Answer 2 - Method overloading is when more than one method has the same name, but is differentiated by the method signature through accepting different parameters. ");
        addQuestion(q6);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_EXPLANATION, question.getExplanation());

        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setExplanation(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLANATION)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}


