package kirdmt.com.realcitizen.ui.constitution;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kirdmt.com.realcitizen.R;
import kirdmt.com.realcitizen.data.ConstitutionData;
import kirdmt.com.realcitizen.ui.content.ContentActivity;

public class ConstitutionCardAdapter extends RecyclerView.Adapter<ConstitutionCardAdapter.ViewHolder> {


    private Context context;
    private List<ConstitutionData> constitutionDataList;
    private String chapterStr;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;


        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public ConstitutionCardAdapter(List<ConstitutionData> constitutionDataList, Context context) {

        this.constitutionDataList = constitutionDataList;
        this.context = context;

    }

    @Override
    public ConstitutionCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_constitution, parent, false);

        return new ViewHolder(cv);

    }

    @Override
    public void onBindViewHolder(final ConstitutionCardAdapter.ViewHolder holder, final int position) {

        chapterStr = constitutionDataList.get(position).getChapterName();

        final CardView cardView = holder.cardView;
        TextView articleName = (TextView) cardView.findViewById(R.id.article_text);

        if (chapterStr != null && chapterStr.length() > 101) {
            String bufChapterStr;
            bufChapterStr = chapterStr.substring(0, 99);
            chapterStr = bufChapterStr;
        }

        articleName.setText(chapterStr);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ContentActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("chapter_name", constitutionDataList.get(position).getChapterName());
                intent.putExtra("chapter_content", constitutionDataList.get(position).getChapterText());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return constitutionDataList.size();
    }

}
