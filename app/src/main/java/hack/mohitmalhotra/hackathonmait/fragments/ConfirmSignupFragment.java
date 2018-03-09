package hack.mohitmalhotra.hackathonmait.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import hack.mohitmalhotra.hackathonmait.AuthActivity;
import hack.mohitmalhotra.hackathonmait.MainActivity;
import hack.mohitmalhotra.hackathonmait.R;

import static android.os.Build.VERSION_CODES.M;


public class ConfirmSignupFragment extends Fragment {


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_confirm_signup, container, false);

        final ImageButton btnBack = (ImageButton) view.findViewById(R.id.btnBack);
        final Button btnSignUp = (Button) view.findViewById(R.id.btnSignup);
        final TextView linkToSignIn = (TextView) view.findViewById(R.id.linkToSignin);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthActivity)getActivity()).previousPage();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        linkToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBack.callOnClick();
            }
        });

        return this.view;
    }


}
