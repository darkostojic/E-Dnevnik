package com.example.sviostali.e_dnevnik.ListViews.UsersListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import java.util.ArrayList;
import java.util.List;

/**
 * Isao kao druga 2 adaptera za prikaz
 */

public class UserListAdapter extends BaseAdapter{
    public List<String> list;
    public List<UserList> userList;
    public Context context;

    public UserListAdapter(Context c){
        context = c;
        userList = new ArrayList<UserList>();

        List<usersugar> allUsers = usersugar.listAll(usersugar.class);

        for(int i = 0;i<allUsers.size();i++) {
            String a = allUsers.get(i).getLogin();
            String b = allUsers.get(i).getPassword();
            int d = allUsers.get(i).getProfessor();

            if ((d == 0)) {
                userList.add(new UserList(allUsers.get(i).getLogin()));
            }

        }
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.subject_list, viewGroup, false);

        TextView user = (TextView) row.findViewById(R.id.tvSubject);
        CheckBox isMarked = (CheckBox) row.findViewById(R.id.cbIsMarked);

        final UserList temp = userList.get(position);

        user.setText(temp.user);
        isMarked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(temp.isMarked()){
                    temp.setMarked(false);
                }else {
                    temp.setMarked(true);
                }
            }
        });

        return row;
    }

    public List<UserList> getUserList(){
        return userList;
    }

    public void setUserList(List<UserList> userList){
        this.userList = userList;
    }
}
