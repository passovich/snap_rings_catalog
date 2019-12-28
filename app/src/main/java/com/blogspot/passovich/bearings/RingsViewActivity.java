package com.blogspot.passovich.bearings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RingsViewActivity extends Activity  {
    private final String TAG = "myLogs";
    private Button buttonImage1,buttonImage2;
    private List<TableRow>tableRowsList = new ArrayList<TableRow>(200);
    private LinearLayout mainLayout;
    private TableLayout tableLayout;

    private HelperMethodClass helperMethodClass = new HelperMethodClass();
    private String nameDB = "Rings.db";
    private String selection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rings_view);

        Intent intent = getIntent();
        selection = intent.getStringExtra("selection");
        buttonImage1 = (Button) findViewById(R.id.button1);
        buttonImage2 = (Button) findViewById(R.id.button2);
        if (selection.equals("_id=1")){buttonImage1.setVisibility(View.VISIBLE); buttonImage2.setVisibility(View.GONE);}
        if (selection.equals("_id=2")){buttonImage2.setVisibility(View.VISIBLE); buttonImage1.setVisibility(View.GONE);}
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        LinearLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

        List<Ring> rings = getRingsCollection();
        tableLayout = getTableRings(rings,this);
        tableLayout.setBackgroundColor(Color.BLACK);
        mainLayout.addView(tableLayout,tableParams);
    }

    private List<Ring> getRingsCollection(){
        List<Ring> rings = new ArrayList<Ring>(200);
        boolean flag=true;
        int counter=0;
        while (flag){
            Ring ring = new Ring(null,null,null,null,null);
            flag = helperMethodClass.onViewDataFromFieldTable(
                    this,
                    nameDB,
                    "Rings",
                    counter,
                    ring,
                    selection
            );
            rings.add(ring);
            counter++;
            Log.d(TAG,ring.toString());
        }
        return rings;
    }
    private TableLayout getTableRings (List<Ring> rings, Context context){
        TableLayout tableLayout = new TableLayout(context);
        for (Ring r:rings){
            TableRow tempTableRow = new TableRow(this);
            tempTableRow.setWeightSum(1);                   //Установка суммы весов элементов строки
            TextView tempTetxView[] = new TextView[5];
            for (int j=0;j<5;j++){
                tempTetxView[j] = new TextView(this);
                float weight=0.15f; if (j==0) weight=0.4f;  //Задание ширины ячеек
                TableRow.LayoutParams textViewsTableParams = new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        weight
                );
                textViewsTableParams.setMargins(1,1,1,1); //установка отступов для сетки
                tempTetxView[j].setLayoutParams(textViewsTableParams);
                tempTetxView[j].setBackgroundColor(Color.LTGRAY);
                tempTetxView[j].setText(r.getColumn(j));
                tempTetxView[j].setGravity(1);
                tempTableRow.addView(tempTetxView[j]);
                tableRowsList.add(tempTableRow);
            }
            tableLayout.addView(tableRowsList.get(tableRowsList.size()-1));
        }
        return tableLayout;
    }
}


