package com.example.sviostali.e_dnevnik.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sviostali.e_dnevnik.ListViews.MarksListView.MarksAdapter;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.marks;
import com.example.sviostali.e_dnevnik.sugarclasses.studentsubject;
import com.orm.SugarContext;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class StudentReview extends AppCompatActivity {

    public TextView tvName, tvAverage, tvFinalMark, tvBirthDate;
    public Button btnSetMark, btnSetFinalMark, btnAutoSetFinalMark;
    public ImageView ivAvatar;
    public long id;
    public float avg, sum;
    public studentsubject stud_sub;
    public marks mark;
    public int setMark;
    public ListView lvMarks;
    public MarksAdapter MAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_student_review);

        tvName = (TextView) findViewById(R.id.tvURname);
        tvAverage = (TextView) findViewById(R.id.tvURaverage);
        tvFinalMark = (TextView) findViewById(R.id.tvURFinalMark);
        tvBirthDate = (TextView) findViewById(R.id.tvURbirthdate);

        btnSetMark = (Button) findViewById(R.id.btnURsetMark);
        btnSetFinalMark = (Button) findViewById(R.id.btnURsetFinalMark);
        btnAutoSetFinalMark = (Button) findViewById(R.id.btnURautosetFinalMark);

        ivAvatar = (ImageView) findViewById(R.id.ivSRavatar);

        Bundle s = getIntent().getExtras();

        id = s.getLong("id");
        stud_sub = studentsubject.findById(studentsubject.class, id);

        MAdapter = new MarksAdapter(this, id);
        lvMarks = (ListView) findViewById(R.id.lvSRmarks);
        lvMarks.setAdapter(MAdapter);


        tvName.setText(stud_sub.getUser().getLogin());
        tvBirthDate.setText(stud_sub.getUser().getBirthdate());

        Glide.with(this)
                .load(stud_sub.getUser().getAvatar())
                .into(ivAvatar);


        if(stud_sub.getFinalmark()>0){
            tvFinalMark.setText("Zaključna ocjena je: "+stud_sub.getFinalmark()+"");
        }
        getAverage();

        //unos ocijene
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ocijena:");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                setMark = Integer.parseInt(input.getText().toString());
                if(setMark<0||setMark>5){
                    Toast.makeText(StudentReview.this, "Unesite ocjenu u rasponu 1-5", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    dialog.dismiss();
                }else{

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = sdf.format(c.getTime());
                    Toast.makeText(StudentReview.this, "Ocijeni li ste s ocjenom: "+setMark, Toast.LENGTH_SHORT).show();
                    mark = new marks(stud_sub, setMark, strDate, 0);
                    mark.save();
                    dialog.cancel();
                    dialog.dismiss();
                    getAverage();

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                dialog.dismiss();
            }
        });



        //zakljucivanje
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Zakljucna ocjena:");
        final EditText input2 = new EditText(this);
        input2.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder2.setView(input2);
        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                setMark = Integer.parseInt(input2.getText().toString());
                if(setMark<0||setMark>5){
                    Toast.makeText(StudentReview.this, "Unesite ocjenu u rasponu 1-5", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    dialog.dismiss();
                }else{

                    Toast.makeText(StudentReview.this, "Zakljucili ste ocjenu: "+setMark, Toast.LENGTH_SHORT).show();
                    stud_sub.setFinalmark(setMark);
                    stud_sub.save();
                    dialog.cancel();
                    dialog.dismiss();
                    tvFinalMark.setText("Zaključna ocjena je: "+setMark);

                }
            }
        });
        builder2.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                dialog.dismiss();
            }
        });




        btnSetMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });

        btnSetFinalMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder2.show();
            }
        });

        btnAutoSetFinalMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tmp = Math.round(avg);
                Toast.makeText(StudentReview.this, "Automatski zaključena ocjena je: "+tmp, Toast.LENGTH_SHORT).show();
                stud_sub.setFinalmark(tmp);
                stud_sub.save();
                tvFinalMark.setText("Zaključna ocjena je: "+tmp);
            }
        });

    }


    private void getAverage() {
        List<marks> list = stud_sub.getMarks();
        if(list.isEmpty()){
            tvAverage.setText("0");
        }else{
            for(int i = 0;i<list.size();i++){
                sum+=list.get(i).getMark();
            }
            avg = sum/list.size();
            tvAverage.setText("Vaš prosjek je: " + avg);
        }

    }
}
