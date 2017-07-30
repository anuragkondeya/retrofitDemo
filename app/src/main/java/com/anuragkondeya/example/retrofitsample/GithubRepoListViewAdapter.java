package com.anuragkondeya.example.retrofitsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anuragkondeya on 30/7/17.
 */

public class GithubRepoListViewAdapter extends ArrayAdapter<GitHubRepo> {

    private List<GitHubRepo> mRepoList;

    public GithubRepoListViewAdapter(@NonNull Context context, List<GitHubRepo> repoList) {
        super(context, R.layout.listcell,repoList);
        mRepoList = repoList;
    }

    private class ViewHolder {
        TextView repoList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final GitHubRepo repo = mRepoList.get(position);


        ViewHolder viewHolder = null;
        View view = null;

        if(null==convertView){
            viewHolder =  new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listcell,parent,false);
            viewHolder.repoList = (TextView) convertView.findViewById(R.id.repoTitle);
            view = convertView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
        }
        viewHolder.repoList.setText(repo.getRepoName());
        return view;
    }
}
