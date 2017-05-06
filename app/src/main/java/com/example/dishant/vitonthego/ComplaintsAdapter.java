package com.example.dishant.vitonthego;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by dishant on 6/5/17.
 */

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Complaint> data = Collections.EMPTY_LIST;

    @Override
    public ComplaintViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.places_view, parent, false);
        ComplaintViewHolder complaintViewHolder = new ComplaintViewHolder(view);
        return complaintViewHolder;
    }

    @Override
    public void onBindViewHolder(ComplaintViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(Complaint info);
    }

    private OnItemClickListener listener;

    public ComplaintsAdapter(Context context, List<Complaint> data, ComplaintsAdapter.OnItemClickListener listener){
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    public class ComplaintViewHolder extends RecyclerView.ViewHolder{

        TextView title, authority, details, intensity;

        public ComplaintViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            authority = (TextView) itemView.findViewById(R.id.location);
            details = (TextView) itemView.findViewById(R.id.desc);
            intensity = (TextView) itemView.findViewById(R.id.rating);

        }

        public void bind(final Complaint info, final ComplaintsAdapter.OnItemClickListener listener){
            title.setText(info.getComplaint_title());
            authority.setText(info.getConcerned_authority());
            details.setText(info.getComplaint_details());
            intensity.setText(info.getComplaint_intensity());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(info);
                }
            });
        }

    }


}
