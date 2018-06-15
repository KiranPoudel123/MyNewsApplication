package myapp.training.newitventure.com.mynewsapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import myapp.training.newitventure.com.mynewsapplication.MainActivity;
import myapp.training.newitventure.com.mynewsapplication.NewsActivity;
import myapp.training.newitventure.com.mynewsapplication.R;
import myapp.training.newitventure.com.mynewsapplication.model.News;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    /*public static final String KEY_TITLE="news_title";
    public static final String KEY_CATEGORY="news_category";
    public static final String KEY_DESCRIPTION="news_description";
    public static final String KEY_PUBLISHEDDATE = "news_publisheddate";
*/


    private List<News> dataList;
    private Context context;

    public MainAdapter(Context context, List<News> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        final News List = dataList.get(position);

        viewHolder.txt_title.setText(List.getTitle());
        viewHolder.txt_description.setText(List.getDescription());
        viewHolder.txt_source.setText(List.getSource());
        viewHolder.txt_category.setText(List.getCategory());
        viewHolder.txt_publisheddate.setText(List.getPublishedDate());
        Picasso.with(context).load(List.getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.imageView);


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"you clicked" + List.getTitle(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(),NewsActivity.class);
                intent.putExtra("title", List.getTitle());
                intent.putExtra("image", List.getImage());
                intent.putExtra("description", List.getDescription());
                intent.putExtra("category", List.getCategory());
                intent.putExtra("publisheddate", List.getPublishedDate());
                view.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount()
    {

        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //AdapterView.OnItemClickListener itemClickListener;

        TextView txt_title;
        TextView txt_description;
        TextView txt_publisheddate;
        TextView txt_category;
        ImageView imageView;
        TextView txt_source;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;


        ViewHolder(View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_description= itemView.findViewById(R.id.txt_description);
            txt_publisheddate = itemView.findViewById(R.id.txt_publisheddate);
            imageView = itemView.findViewById(R.id.imageView);
            txt_category = itemView.findViewById(R.id.txt_category);
            txt_source = itemView.findViewById(R.id.txt_source);
            linearLayout = itemView.findViewById(R.id.linearLayout);


        }
    }

}

