package com.aniik.pdfcreator;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  String[] header ={"Serial","Medicine Name","Time"};
    private String shortText="Patient Name: Mr/Mrs. Kamal Uddin\t Gender: Male \t Age: 40 years";
    private String longText="Thank You For Visiting\n-Dr.Noor Mohammed Anik";

    private TemplatePDF templatePDF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        templatePDF=new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Clients","Ventas","Anik");
        templatePDF.addTitle("Dr. Noor Mohammed Anik","Medicine Specialist\n(MBBS(CMC),FCPS(USA))\nPatient Prescription","24/07/2018");
        templatePDF.addParagraph(shortText);
       // templatePDF.addParagraph(longText);

        templatePDF.createTable(header,getClients());
        templatePDF.addRightParagraph(longText);
        templatePDF.closeDocument();

    }

    public void pdfView(View view)
    {

        templatePDF.viewPDF();
    }


    public void pdfApp(View view)
    {

        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        templatePDF.appViewPDF(MainActivity.this);
    }

    private ArrayList<String []>getClients(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[] {"1","Napa Extra","1+0+1"});
        rows.add(new String[] {"2","Paracetamol","0+1+1"});
        rows.add(new String[] {"3","Esonis 75mg","1+0+0"});
        rows.add(new String[] {"4","Bizoran 2.5 mg","0+0+0"});
        return rows;
    }
}
