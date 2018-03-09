package hack.mohitmalhotra.hackathonmait.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import hack.mohitmalhotra.hackathonmait.R;
import hack.mohitmalhotra.hackathonmait.adapters.CustomItemAdapter;

/**
 * Created by MOHIT MALHOTRA on 09-03-2018.
 */

public class BorrowFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_borrow, container, false);
        final ListView lvItem = (ListView) view.findViewById(R.id.lvItem);

        CustomItemAdapter adapter = new CustomItemAdapter(getActivity());
        lvItem.setAdapter(adapter);

        return this.view;
    }


}
