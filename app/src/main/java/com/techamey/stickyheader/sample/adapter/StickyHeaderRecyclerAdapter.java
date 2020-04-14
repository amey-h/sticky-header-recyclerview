package com.techamey.stickyheader.sample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techamey.stickyheader.sample.R;
import com.techamey.stickyheader.sample.model.DataModel;
import com.techamey.stickyheader.sample.model.ItemType;
import com.techamey.stickyheader.sample.model.ListDataModel;
import com.techamey.stickyheaderlib.StickyHeaderItemDecoration;

import java.util.List;

public class StickyHeaderRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements StickyHeaderItemDecoration.StickyHeaderInterface {

    private Context context;
    private List<DataModel> dataModelList;
    private static final String TAG = "StickyHeader";

    public StickyHeaderRecyclerAdapter(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
        Log.d(TAG, "dataModelList : " + dataModelList.size());
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView headerTextView;

        public HeaderViewHolder(@NonNull View view) {
            super(view);
            headerTextView = view.findViewById(R.id.text_header);
        }

        public void bind(ListDataModel dataModel) {
            String header = dataModel.getHeader();
            Log.d(TAG, "Header : " + header);
            headerTextView.setText(header);
        }
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView dataTextView;

        public DataViewHolder(@NonNull View view) {
            super(view);
            dataTextView = view.findViewById(R.id.text_general);
        }

        public void bind(ListDataModel dataModel) {
            String data = dataModel.getData();
            Log.d(TAG, "Data : " + data);
            dataTextView.setText(data);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Log.d(TAG, "ViewType: " + viewType);
        if (viewType == ItemType.TYPE_HEADER) {
            View view1 = inflater.inflate(R.layout.list_header_item, parent,
                    false);
            viewHolder = new HeaderViewHolder(view1);
        } else if (viewType == ItemType.TYPE_DATA) {
            View view2 = inflater.inflate(R.layout.list_general_item, parent,
                    false);
            viewHolder = new DataViewHolder(view2);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {

            case ItemType.TYPE_HEADER:
                ((HeaderViewHolder) viewHolder).bind(dataModelList.get(position).getListDataModel());
                break;

            case ItemType.TYPE_DATA:
                ((DataViewHolder) viewHolder).bind(dataModelList.get(position).getListDataModel());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataModelList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        int headerPosition = 0;
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition;
                break;
            }
            itemPosition -= 1;
        } while (itemPosition >= 0);
        return headerPosition;
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        if (dataModelList.get(headerPosition).getType() == ItemType.TYPE_HEADER)
            return R.layout.list_header_item;
        else {
            return R.layout.list_general_item;
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        TextView tv = header.findViewById(R.id.text_header);
        DataModel dataModel = dataModelList.get(headerPosition);
        tv.setText(dataModel.getListDataModel().getHeader());
    }

    @Override
    public boolean isHeader(int itemPosition) {
        //this check added if it is on same screen.
        if (itemPosition >= dataModelList.size()) {
            return false;
        } else {
            return dataModelList.get(itemPosition).getType() == ItemType.TYPE_HEADER;
        }
    }
}
