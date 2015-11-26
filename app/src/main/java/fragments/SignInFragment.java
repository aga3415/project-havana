package fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
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
import rest.Login;
import storageData.SignInPreferences;

/**
 * Created by Agnieszka on 2015-11-04.
 */
public class SignInFragment extends Fragment {

    public static final String ARG_PAGE = "Fragment Sign In";
    public static TextView text;
    public static EditText login;
    public static EditText password;
    public static Button signIn;
    private static SignInFragment instance = null;
    public static RelativeLayout signInRelativeLayout;
    public static ProgressBar startProgressBar;
    CheckBox rememberMe;
    View view;
    Login loginTask;
    boolean isrememberUser = false;

    private int mPage;

    public static SignInFragment getInstance() {
        if (instance != null){
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, 0);
            SignInFragment fragment = new SignInFragment();
            fragment.setArguments(args);
            return fragment;
        }else{
            return instance;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = 0;
        instance = this;

        //SignInPreferences.deletePreferences(SignInUp.instance);
        Pair pref = SignInPreferences.checkPreferences(SignInUp.instance);

        if (pref != null){
            isrememberUser = true;
            loginTask = new Login(SignInFragment.getInstance());
            trySignIn((String) pref.first, (String) pref.second);
        }else{
            isrememberUser = false;
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        instance = this;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.fragment_sign_in_portrait, container, false);
        }else {
            view = inflater.inflate(R.layout.fragment_sign_in_landscape, container, false);
        }

        text = (TextView) view.findViewById(R.id.signInErrorText);
        login = (EditText) view.findViewById(R.id.loginText);
        password = (EditText) view.findViewById(R.id.passwordText);
        signIn = (Button) view.findViewById(R.id.loginButton);
        signInRelativeLayout = (RelativeLayout) view.findViewById(R.id.editTextRelativeLayout);
        startProgressBar = (ProgressBar) view.findViewById(R.id.startProgress);
        rememberMe = (CheckBox) view.findViewById(R.id.rememberMe);
        loginTask = new Login(SignInFragment.getInstance());

        if (!isrememberUser){
            signInRelativeLayout.setVisibility(View.VISIBLE);
            startProgressBar.setVisibility(View.GONE);
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startProgressBar.setVisibility(View.VISIBLE);
                signInRelativeLayout.setVisibility(View.GONE);

                String loginString = login.getText().toString();
                String passwordString = password.getText().toString();
                text.setText("");
                if (rememberMe.isChecked()){
                    SignInPreferences.setPreferences(SignInUp.instance, loginString, passwordString);
                }

                loginTask.execute(loginString, passwordString);
            }
        });



        return view;
    }

    public void trySignIn(String email, String password){
        loginTask.execute(email, password);
    }


}
