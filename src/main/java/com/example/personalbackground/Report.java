package com.example.personalbackground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyDisplayInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.prefs.PreferenceChangeEvent;

public class Report extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "myCode";
    private SharedPreferences shareToReport;

    private ImageView imgViewPhoto;
    private TextView textName;
    private TextView textPhone;
    private TextView textEmailAddress;
    private TextView textHeight;

    private TextView textWeight;
    private TextView textGender;
    private TextView textCivilStatus;
    private TextView textPagibig;
    private TextView textPhilhealth;
    private TextView textTin;
    private TextView textGsis;
    private TextView textContactName;
    private TextView textContactNumber;
    private TextView textRelationship;
    private TextView textElemSchool;
    private TextView textElemEd;
    private TextView textSecondarySchool;
    private TextView textSecondaryEd;
    private TextView textVocationalSchool;
    private TextView textVocationalEd;
    private TextView textCollegeSchool;
    private TextView textCollegeEd;
    private TextView textGraduateStudiesSchool;
    private TextView textGraduateStudiesEd;
    private TextView textQ1;
    private TextView textQ2;
    private TextView textQ3;
    private TextView textQA;
    private TextView textQB;
    private TextView textQC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        shareToReport = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        imgViewPhoto = findViewById(R.id.imgViewPhoto);
        textName = findViewById(R.id.textName);
        textPhone = findViewById(R.id.textPhone);
        textEmailAddress = findViewById(R.id.textEmailAddress);
        textHeight = findViewById(R.id.textHeight);
        textWeight = findViewById(R.id.textWeight);
        textGender = findViewById(R.id.textGender);
        textCivilStatus = findViewById(R.id.textCivilStatus);
        textPagibig = findViewById(R.id.textPagibig);
        textPhilhealth = findViewById(R.id.textPhilhealth);
        textTin = findViewById(R.id.textTin);
        textGsis = findViewById(R.id.textGsis);
        textContactName = findViewById(R.id.textContactName);
        textContactNumber = findViewById(R.id.textContactNumber);
        textRelationship = findViewById(R.id.textRelationship);

        // Personal Background
        String name = shareToReport.getString("Name", "N/A");
        String phone = shareToReport.getString("Phone", "N/A");
        String email = shareToReport.getString("Email", "N/A");
        String height = shareToReport.getString("Height", "N/A");
        String weight = shareToReport.getString("Weight", "N/A");
        String gender = shareToReport.getString("Gender", "N/A");
        String relationship = shareToReport.getString("Relationship", "N/A");
        String pagibig = shareToReport.getString("Pagibig", "N/A");
        String philhealth = shareToReport.getString("PhilHealth", "N/A");
        String tin = shareToReport.getString("Tin", "N/A");
        String gsis = shareToReport.getString("Gsis", "N/A");
        String civilStatus = shareToReport.getString("CivilStatus", "N/A");
        String contact = shareToReport.getString("Contact", "N/A");
        String contactname = shareToReport.getString("ContactName", "N/A");

        textName.setText(name);
        textPhone.setText(phone);
        textEmailAddress.setText(email);
        textHeight.setText(height);
        textWeight.setText(weight);
        textGender.setText(gender);
        textRelationship.setText(relationship);
        textPagibig.setText(pagibig);
        textPhilhealth.setText(philhealth);
        textTin.setText(tin);
        textGsis.setText(gsis);
        textCivilStatus.setText(civilStatus);
        textContactName.setText(contactname);
        textContactNumber.setText(contact);

        // Educational Background
        textElemSchool = findViewById(R.id.textElemSchool);
        textElemEd = findViewById(R.id.textElemEd);
        textSecondarySchool = findViewById(R.id.textSecondarySchool);
        textSecondaryEd = findViewById(R.id.textSecondaryEd);
        textVocationalSchool = findViewById(R.id.textVocationalSchool);
        textVocationalEd = findViewById(R.id.textVocationalEd);
        textCollegeSchool = findViewById(R.id.textCollegeSchool);
        textCollegeEd = findViewById(R.id.textCollegeEd);
        textGraduateStudiesSchool = findViewById(R.id.textGraduateStudiesSchool);
        textGraduateStudiesEd = findViewById(R.id.textGraduateStudiesEd);

        String elemSchool = shareToReport.getString("ElemSchool", "N/A");
        String elemEd = shareToReport.getString("ElemEd", "N/A");
        String secondarySchool = shareToReport.getString("SecSchool", "N/A");
        String secondaryEd = shareToReport.getString("SecEd", "N/A");
        String vocationalSchool = shareToReport.getString("VocSchool", "N/A");
        String vocationalEd = shareToReport.getString("VocEd", "N/A");
        String collegeSchool = shareToReport.getString("ColSchool", "N/A");
        String collegeEd = shareToReport.getString("ColEd", "N/A");
        String graduateStudiesSchool = shareToReport.getString("GradStudSchool", "N/A");
        String graduateStudiesEd = shareToReport.getString("GradStudEd", "N/A");

        textElemSchool.setText(elemSchool);
        textElemEd.setText(elemEd);
        textSecondarySchool.setText(secondarySchool);
        textSecondaryEd.setText(secondaryEd);
        textVocationalSchool.setText(vocationalSchool);
        textVocationalEd.setText(vocationalEd);
        textCollegeSchool.setText(collegeSchool);
        textCollegeEd.setText(collegeEd);
        textGraduateStudiesSchool.setText(graduateStudiesSchool);
        textGraduateStudiesEd.setText(graduateStudiesEd);


        //Criminal Background
        textQ1 = (TextView) findViewById(R.id.textQ1);
        textQ2 = (TextView) findViewById(R.id.textQ2);
        textQ3 = (TextView) findViewById(R.id.textQ3);
        textQA = (TextView) findViewById(R.id.textQA);
        textQB = (TextView) findViewById(R.id.textQB);
        textQC = (TextView) findViewById(R.id.textQC);

        String questionOne = shareToReport.getString("QuestionOne", "N/A");
        String questionTwo = shareToReport.getString("QuestionTwo", "N/A");
        String questionThree = shareToReport.getString("QuestionThree", "N/A");
        String questionA = shareToReport.getString("QuestionA", "N/A");
        String questionB = shareToReport.getString("QuestionB", "N/A");
        String questionC = shareToReport.getString("QuestionC", "N/A");

        textQ1.setText(questionOne);
        textQ2.setText(questionTwo);
        textQ3 .setText(questionThree);
        textQA.setText(questionA);
        textQB.setText(questionB);
        textQC.setText(questionC);

    }
}
