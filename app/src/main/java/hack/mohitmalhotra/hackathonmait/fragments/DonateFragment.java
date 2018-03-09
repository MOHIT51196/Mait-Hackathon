package hack.mohitmalhotra.hackathonmait.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hack.mohitmalhotra.hackathonmait.DetailsActivity;
import hack.mohitmalhotra.hackathonmait.R;


public class DonateFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_donate, container, false);
        final Button btnGoToBorrow = (Button) view.findViewById(R.id.btnGoToBorrow);

        btnGoToBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailsActivity)getActivity()).nextPage();
            }
        });

        return this.view;
    }


}
