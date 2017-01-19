package com.example.allenduncare.kingguardian;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class deckViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SQLiteDatabase db = openOrCreateDatabase("deckDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Deck(card VARCHAR,num INTEGER);");
        Cursor result = db.rawQuery("SELECT * FROM Deck", null);
        setContentView(R.layout.activity_deck_viewer);
        final NumberPicker np = (NumberPicker) findViewById(R.id.earthpicker);
        final NumberPicker np1 = (NumberPicker) findViewById(R.id.airpicker);
        final NumberPicker np2 = (NumberPicker) findViewById(R.id.firepicker);
        final NumberPicker np3 = (NumberPicker) findViewById(R.id.waterpicker);
        np.setMinValue(0);
        np.setMaxValue(40);
        np1.setMinValue(0);
        np1.setMaxValue(40);
        np2.setMinValue(0);
        np2.setMaxValue(40);
        np3.setMinValue(0);
        np3.setMaxValue(40);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int sum = np.getValue() + np1.getValue() + np2.getValue() + np3.getValue();
                TextView tv = (TextView) findViewById(R.id.totalView);
                tv.setText(String.valueOf(sum));
            }
        });
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int sum = np.getValue() + np1.getValue() + np2.getValue() + np3.getValue();
                TextView tv = (TextView) findViewById(R.id.totalView);
                tv.setText(String.valueOf(sum));
            }
        });
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int sum = np.getValue() + np1.getValue() + np2.getValue() + np3.getValue();
                TextView tv = (TextView) findViewById(R.id.totalView);
                tv.setText(String.valueOf(sum));
            }
        });
        np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int sum = np.getValue() + np1.getValue() + np2.getValue() + np3.getValue();
                TextView tv = (TextView) findViewById(R.id.totalView);
                tv.setText(String.valueOf(sum));
            }
        });
        int row_count = result.getCount();
        if (row_count == 0)
        {
            db.execSQL("INSERT INTO Deck Values('Earth',0)");
            db.execSQL("INSERT INTO Deck Values('Air',0)");
            db.execSQL("INSERT INTO Deck Values('Fire',0)");
            db.execSQL("INSERT INTO Deck Values('Water',0)");}
        result = db.rawQuery("SELECT * FROM Deck", null);
        result.moveToFirst();
        int cardSum = result.getInt(1);
        result.moveToNext();
        cardSum += result.getInt(1);
        result.moveToNext();
        cardSum += result.getInt(1);
        result.moveToNext();
        cardSum += result.getInt(1);
        switch (cardSum){
            case 0:
                Toast.makeText(getApplicationContext(),"No deck was found!",Toast.LENGTH_LONG).show();
                break;
            case 40:
                TextView tv = (TextView) findViewById(R.id.totalView);
                tv.setText(String.valueOf(40));
                result.moveToFirst();
                np.setValue(result.getInt(1));
                result.moveToNext();
                np1.setValue(result.getInt(1));
                result.moveToNext();
                np2.setValue(result.getInt(1));
                result.moveToNext();
                np3.setValue(result.getInt(1));
                break;



        }
        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView)findViewById(R.id.totalView);
                String s = tv.getText().toString();
                int sum = Integer.parseInt(s);
                if(sum == 40)
                {
                    int EarthCard = np.getValue();
                    int AirCard = np1.getValue();
                    int FireCard = np2.getValue();
                    int WaterCard= np3.getValue();
                    ContentValues content = new ContentValues();
                    content.put("num",EarthCard);
                    db.update("Deck",content,"card='Earth'",null);
                    ContentValues content1 = new ContentValues();
                    content1.put("num",AirCard);
                    db.update("Deck",content1,"card='Air'",null);
                    ContentValues content2 = new ContentValues();
                    content2.put("num",FireCard);
                    db.update("Deck",content2,"card='Fire'",null);
                    ContentValues content3 = new ContentValues();
                    content3.put("num",WaterCard);
                    db.update("Deck",content3,"card='Water'",null);
                    finish();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"You do not have the right amount of cards!",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
