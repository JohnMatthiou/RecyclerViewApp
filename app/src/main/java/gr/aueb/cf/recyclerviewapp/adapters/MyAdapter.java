package gr.aueb.cf.recyclerviewapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import gr.aueb.cf.recyclerviewapp.R;
import gr.aueb.cf.recyclerviewapp.models.Places;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<Places> placesArrayList;
    ArrayList<Places> placesArrayListFull;

    public MyAdapter(Context context, ArrayList<Places> placesArrayList) {
        this.context = context;
        this.placesArrayListFull = placesArrayList;
        this.placesArrayList = new ArrayList<>(placesArrayListFull);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Places places = placesArrayList.get(position);
        holder.headingTitle.setText(places.getHeading());
        holder.placeImage.setImageResource(places.getPlaceImage());
        holder.shortDescription.setText(places.getShortDescription());

        boolean isVisible = places.isVisibility();
        holder.constraintLayout.setVisibility(isVisible? View.VISIBLE: View.GONE);
    }

    @Override
    public int getItemCount() {
        return placesArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return placesFilter;
    }

    public final Filter placesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Places> filteredPlacesList = new ArrayList<>();
            if (constraint == null || constraint.length()==0) {
                filteredPlacesList.addAll(placesArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Places places: placesArrayListFull) {
                    if (places.getHeading().toLowerCase().contains(filterPattern)) {
                        filteredPlacesList.add(places);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredPlacesList;
            results.count = filteredPlacesList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            placesArrayList.clear();
            placesArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView headingTitle;
        ShapeableImageView placeImage;
        TextView shortDescription;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            headingTitle = itemView.findViewById(R.id.heading_text);
            placeImage = itemView.findViewById(R.id.image_place);
            shortDescription = itemView.findViewById(R.id.short_description);
            constraintLayout = itemView.findViewById(R.id.expanded_layout);

            headingTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Places places = placesArrayList.get(getAdapterPosition());
                    places.setVisibility(!places.isVisibility());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
