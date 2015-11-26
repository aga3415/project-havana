package fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import limiszewska.projecthavana.activities.R;
import limiszewska.projecthavana.activities.SignInUp;
import rest.Register;
import storageData.SignInPreferences;

/**
 * Created by Agnieszka on 2015-11-04.
 */
public class SignUpFragment extends Fragment {

    public static final String ARG_PAGE = "Fragment Sign Up";
    public static TextView text;
    public static EditText login;
    public static EditText email;
    public static EditText password;
    public static Button signUp;
    private static SignUpFragment instance = null;
    public static RelativeLayout signUpRelativeLayout;
    public static ProgressBar startProgressBar;
    CheckBox rememberMe;
    View view;


    private int mPage;

    public static SignUpFragment getInstance() {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, 1);
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mPage = 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        instance = this;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.fragment_sign_up_portrait, container, false);
        }else {
            view = inflater.inflate(R.layout.fragment_sign_up_landscape, container, false);
        }
        text = (TextView) view.findViewById(R.id.signInErrorText);
        login = (EditText) view.findViewById(R.id.loginText);
        email = (EditText) view.findViewById(R.id.emailText);
        password = (EditText) view.findViewById(R.id.passwordText);
        signUp = (Button) view.findViewById(R.id.registerButton);
        startProgressBar = (ProgressBar) view.findViewById(R.id.startProgress);
        signUpRelativeLayout = (RelativeLayout) view.findViewById(R.id.editTextRelativeLayout);
        rememberMe = (CheckBox) view.findViewById(R.id.rememberMe);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register registerTask = new Register(SignUpFragment.getInstance());
                startProgressBar.setVisibility(View.VISIBLE);
                signUpRelativeLayout.setVisibility(View.GONE);

                String loginString = login.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                text.setText("");
                if (rememberMe.isChecked()){
                    SignInPreferences.setPreferences(SignInUp.instance, loginString, passwordString);
                }

                registerTask.execute(loginString, emailString, passwordString);
            }
        });


        return view;
    }
}
