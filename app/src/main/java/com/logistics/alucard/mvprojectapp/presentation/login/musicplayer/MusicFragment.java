package com.logistics.alucard.mvprojectapp.presentation.login.musicplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.logistics.alucard.mvprojectapp.R;
import com.logistics.alucard.mvprojectapp.Services.MusicPlayer;
import com.logistics.alucard.mvprojectapp.bases.BaseFragment;
import com.logistics.alucard.mvprojectapp.data.model.User;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MusicFragment extends BaseFragment implements MusicContract.View {
    private static final String TAG = "MusicFragment";

    TextView tvWelcome, tvPlaylist;
    EditText etUsername, etPass, etEmail;
    Button btLogout;
    Button btPlay, btStop, btNext, btPrev;
    MusicContract.Presenter mPresenter;
    MusicPresenter musicPresenter;

    String usernameDet, passwordDet, emailDet;
    int idDet;
    String email, emailID;
    String[] playList = {"Warbringer Jaina", "Linkin Park - Numb", "Metallica - Fade To Black"};
    ListView lvPlaylist;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(getLayoutId(), container, false);
            tvWelcome = v.findViewById(R.id.tvWelcome);
            tvPlaylist = v.findViewById(R.id.tvMusic);
            lvPlaylist = v.findViewById(R.id.lvMusicList);

            btPlay = v.findViewById(R.id.btPlay);
            btStop = v.findViewById(R.id.btStop);
            btNext = v.findViewById(R.id.btNext);
            btPrev = v.findViewById(R.id.btPrev);

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.tvSong, playList);

            lvPlaylist.setAdapter(adapter);

            lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getActivity(), MusicPlayer.class);
                    i.putExtra("lvCommand", "play");
                    i.putExtra("lvPosition", position);
                    getActivity().startService(i);
                }
            });

            btLogout = v.findViewById(R.id.btLogout);

            emailID = getArguments().getString("emailID");

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: play music");
                Intent i = new Intent(getActivity(), MusicPlayer.class);
                i.putExtra("command", "play");
                getActivity().startService(i);
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MusicPlayer.class);
                getActivity().stopService(i);
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MusicPlayer.class);
                i.putExtra("command", "next");
                getActivity().startService(i);
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MusicPlayer.class);
                i.putExtra("command", "prev");
                getActivity().startService(i);
            }
        });




        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicPresenter.logOut(email);
            }
        });

        return v;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void loadUserDetail(User user) {
        idDet = user.getUser_id();
        usernameDet = user.getUser();
        passwordDet = user.getPass();
        emailDet = user.getEmail();
        Log.d(TAG, "loadUserDetail: username: " + usernameDet);

        if (usernameDet != null){
            setData();
        }
    }

    private void setData(){
        etUsername.setText(usernameDet);
        etPass.setText(passwordDet);
        etEmail.setText(emailDet);

        tvWelcome.setText("Welcome: " + usernameDet);
    }

    @Override
    public void showSuccess(String message) {
        showMessageToast(message);
    }

    @Override
    public void showFailed(String message) {
        showMessageToast(message);
    }

    @Override
    public void navigateToNextPage() {

    }

    @Override
    public void refreshPage(String email) {
        musicPresenter = new MusicPresenter(this, getActivity());
        musicPresenter.getUserEmail(email);
    }

    @Override
    public void logOut() {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(MusicContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: emailValue: " + emailID);
        musicPresenter = new MusicPresenter(this, getActivity());
        //musicPresenter.getUserEmail(emailID);
    }
}
