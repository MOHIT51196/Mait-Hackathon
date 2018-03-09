package hack.mohitmalhotra.hackathonmait.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import hack.mohitmalhotra.hackathonmait.R;

/**
 * Created by MOHIT MALHOTRA on 09-03-2018.
 */

public class CustomItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    public CustomItemAdapter(Context context){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v = inflater.inflate(R.layout.layout_borrow_item, parent, false);

        return null;
    }
}
