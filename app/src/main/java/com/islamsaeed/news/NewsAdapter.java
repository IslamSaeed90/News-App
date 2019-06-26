package com.islamsaeed.news;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.islamsaeed.news.API.Model.ArticlesItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHoler> {
    List<ArticlesItem> articlesItems ;

    public NewsAdapter(List<ArticlesItem> articlesItems) {
        this.articlesItems = articlesItems;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.item_news_list,viewGroup,false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int pos) {

        ArticlesItem item =articlesItems.get(pos);
        viewHoler.title.setText(item.getTitle());
        viewHoler.date.setText(item.getPublishedAt());

        // Image ->> glide , picasso
        Glide.with(viewHoler.itemView)
                .load(item.getUrlToImage())
                .into(viewHoler.imageView);

    }

    @Override
    public int getItemCount() {
        if (articlesItems==null) return 0;
        return articlesItems.size();
    }


    public void changeData (List<ArticlesItem> items){
        articlesItems=items;
        notifyDataSetChanged();

    }



    class ViewHoler extends RecyclerView.ViewHolder{

        TextView date ,title ;
        ImageView imageView ;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
