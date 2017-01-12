package com.mastashake08.androidnetworkdiscovery;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "Network Service";
    private static final String SERVICE_TYPE = "_http._tcp" ;
    NsdManager mNsdManager;
    Button mButton;
    TextView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mNsdManager = (NsdManager) getSystemService(Context.NSD_SERVICE);
        mView = (TextView)findViewById(R.id.network_details);
        mButton = (Button)findViewById(R.id.start_network_discovery);
        mButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Clicked");
                //startNetworkDiscovery(v);
                mView.setText(getIPAddresses(v));
            }
        });
    }

    private String getIPAddresses(View v) {
        String ips = "";
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            while(nis.hasMoreElements())
            {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();
                while (ias.hasMoreElements())
                {
                    InetAddress ia = (InetAddress) ias.nextElement();
                    ips += ia.toString() +"\n";
                    Log.i(TAG,ia.toString());
                }

            }

        } catch (SocketException ex) {
            Log.e(TAG,ex.toString());
        }
        return ips;
    }


    }

