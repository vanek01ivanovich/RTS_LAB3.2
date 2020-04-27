package com.example.perceptron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Constants {



    Switch switchA;
    Switch switchB ;
    Switch switchC ;
    Switch switchD ;
    Switch choose ;
    TextView iterations;
    TextView speed;
    TextView deadlineS;
    TextView success;
    TextView W1;
    TextView W2;
    TextView y1;
    TextView y2;
    TextView y3;
    TextView y4;
    TextView times;
    TextView iter;




    Boolean switchStateA;
    Boolean switchStateB;
    Boolean switchStateC;
    Boolean switchStateD;
    Boolean switchStateChoose;

    int count = 0;
    int flag = 0;

    double w1 = 0;
    double w2 = 0;
    double y = 0;
    double delta = 0;
    double speedValue = 0;
    int iterationsValue = 0;
    boolean[] yResult = {false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchA = (Switch) findViewById(R.id.switchA);
        switchB = (Switch) findViewById(R.id.switchB);
        switchC = (Switch) findViewById(R.id.switchC);
        switchD = (Switch) findViewById(R.id.switchD);
        choose = (Switch) findViewById(R.id.choose);
        iterations = (TextView) findViewById(R.id.iterations);
        W1 = (TextView) findViewById(R.id.w1);
        W2 = (TextView) findViewById(R.id.w2);
        y1 = (TextView) findViewById(R.id.y1);
        y2 = (TextView) findViewById(R.id.y2);
        y3 = (TextView) findViewById(R.id.y3);
        y4 = (TextView) findViewById(R.id.y4);
        times = (TextView) findViewById(R.id.times);
        iter = (TextView) findViewById(R.id.iter);
        success = (TextView) findViewById(R.id.success);
        speed = (TextView) findViewById(R.id.speed);
        deadlineS = (TextView) findViewById(R.id.deadlineS);
        registerForContextMenu(iterations);
        registerForContextMenu(speed);
        registerForContextMenu(deadlineS);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.iterations){
            menu.add(0, MENU_ITERATIONS_1, 0, "100");
            menu.add(0, MENU_ITERATIONS_2, 0, "200");
            menu.add(0, MENU_ITERATIONS_3, 0, "500");
            menu.add(0, MENU_ITERATIONS_4, 0, "1000");
        }else if(v.getId() == R.id.speed){
            menu.add(0, MENU_SPEED_1, 0, "0.001");
            menu.add(0, MENU_SPEED_2, 0, "0.01");
            menu.add(0, MENU_SPEED_3, 0, "0.05");
            menu.add(0, MENU_SPEED_4, 0, "0.1");
            menu.add(0, MENU_SPEED_5, 0, "0.2");
            menu.add(0, MENU_SPEED_6, 0, "0.3");
        }else if(v.getId() == R.id.deadlineS){
            menu.add(0, MENU_DEADLINE_1, 0, "0.5");
            menu.add(0, MENU_DEADLINE_2, 0, "1");
            menu.add(0, MENU_DEADLINE_3, 0, "2");
            menu.add(0, MENU_DEADLINE_4, 0, "5");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
        case MENU_ITERATIONS_1:
            iterations.setText("100");
            break;
        case MENU_ITERATIONS_2:
            iterations.setText("200");
            break;
        case MENU_ITERATIONS_3:
            iterations.setText("500");
            break;
        case MENU_ITERATIONS_4:
            iterations.setText("1000");
            break;
        case MENU_SPEED_1:
            speed.setText("0.001");
            break;
        case MENU_SPEED_2:
            speed.setText("0.01");
            break;
        case MENU_SPEED_3:
            speed.setText("0.05");
            break;
        case MENU_SPEED_4:
            speed.setText("0.1");
            break;
        case MENU_SPEED_5:
            speed.setText("0.2");
            break;
        case MENU_SPEED_6:
            speed.setText("0.3");
            break;
        case MENU_DEADLINE_1:
            deadlineS.setText("0.5");
            break;
        case MENU_DEADLINE_2:
            deadlineS.setText("1");
            break;
        case MENU_DEADLINE_3:
            deadlineS.setText("2");
            break;
        case MENU_DEADLINE_4:
            deadlineS.setText("5");
            break;

        }

        return super.onContextItemSelected(item);
    }



    public void onSwitchClick(View view){
        switchStateA = switchA.isChecked();
        switchStateB = switchB.isChecked();
        switchStateC = switchC.isChecked();
        switchStateD = switchD.isChecked();
        switchStateChoose = choose.isChecked();
        if (switchStateChoose){
            deadlineS.setEnabled(false);
            iterations.setEnabled(true);
        }else{
            deadlineS.setEnabled(true);
            iterations.setEnabled(false);
        }
    }

    public void onClickMakeOperations(View view){
        W1.setText(null);
        W2.setText(null);
        y1.setText(null);
        y2.setText(null);
        y3.setText(null);
        y4.setText(null);
        success.setText(null);
        times.setText(null);
        iter.setText(null);

        w1 = 0;
        w2 = 0;
        y = 0;
        delta = 0;
        count = 0;
        flag = 0;
        speedValue = Double.parseDouble(speed.getText().toString());



        if (switchStateChoose){
            iterationsValue = Integer.parseInt(iterations.getText().toString());
        }else{

            switch (deadlineS.getText().toString()){
                case "0.5":
                    iterationsValue = 100;
                    break;
                case "1":
                    iterationsValue = 200;
                    break;
                case "2":
                    iterationsValue = 500;
                    break;
                case "3":
                    iterationsValue = 1000;
                    break;
            }
        }



        boolean nextOperation = false;
        double startTime = System.nanoTime();
        while(count < iterationsValue){
            flag = 0;
            for (int i = 0; i < points.length; i++) {
                y = getY(points[i][0],points[i][1]);
                delta = getDelta();

                switch(i){
                    case 0:
                        nextOperation = makeOperations(i,switchStateA);
                        break;
                    case 1:
                        nextOperation = makeOperations(i,switchStateB);
                        break;
                    case 2:
                        nextOperation = makeOperations(i,switchStateC);
                        break;
                    case 3:
                        nextOperation = makeOperations(i,switchStateD);
                        break;
                }

                if (!nextOperation){
                    count++;
                    break;
                }


            }

            if (flag == points.length){
                break;
            }
        }
        double endTime = System.nanoTime();
        double time = endTime - startTime;
        time /= 1000000;

        times.append(Double.toString(time));
        if (count == iterationsValue){
            success.setText("Unsuccessfully,increase iterations");
            success.setTextSize(14);
            iter.append(Integer.toString(count));
        }else{
            success.setText("Successfully");
            success.setTextSize(18);
            iter.append(Integer.toString(count));
        }

        W1.append(String.format("%.6s",Double.toString(w1)));
        W2.append(String.format("%.6s",Double.toString(w2)));

        for (int i = 0; i < points.length; i++) {

            switch(i){
                case 0:
                    y1.append(String.format("%.6s",Double.toString((getY(points[i][0],points[i][1])))));
                    break;
                case 1:
                    y2.append(String.format("%.6s",Double.toString((getY(points[i][0],points[i][1])))));
                    break;
                case 2:
                    y3.append(String.format("%.6s",Double.toString((getY(points[i][0],points[i][1])))));
                    break;
                case 3:
                    y4.append(String.format("%.6s",Double.toString((getY(points[i][0],points[i][1])))));
                    break;
            }

        }

    }

    public boolean makeOperations(int i,boolean switchStateABCD){
        //switch true (A,B,C,D) < P
        //switch false (A,B,C,D) > P
        if (switchStateABCD){
            if (y < P){
                flag++;
                return true;
            }else{
                w1 += delta*points[i][0]*speedValue;
                w2 += delta*points[i][1]*speedValue;
                return false;
            }

        }else{
            if (y > P){
                flag++;
                return true;
            }else{
                w1 += delta*points[i][0]*speedValue;
                w2 += delta*points[i][1]*speedValue;
                return false;
            }
        }
    }


    public double getDelta(){
        return delta = P-y;
    }

    public double getY(int point1,int point2){
        return y = point1*w1 + point2*w2;
    }

}
