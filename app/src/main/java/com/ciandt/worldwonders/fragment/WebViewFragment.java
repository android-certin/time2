package com.ciandt.worldwonders.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;

/**
 * Created by wgomes on 27/08/15.
 */
public class WebViewFragment extends DialogFragment {

    public Wonder wonder;


    public static DialogFragment show(FragmentManager fm) {
        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.show(fm, "progress_dialog_fragment");

        return webViewFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.webview_fragment, null);

        wonder = (Wonder) getArguments().getSerializable("wonder");

        WebView webView = (WebView) view.findViewById(R.id.webView);
        webView.loadUrl(wonder.getUrl());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();

        return alertDialog;
    }
}
