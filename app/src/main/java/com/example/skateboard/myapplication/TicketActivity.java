package com.example.skateboard.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ticketmaster.amgr.sdk.app.AmgrConfig;
import com.ticketmaster.amgr.sdk.app.AmgrGlobal;
import com.ticketmaster.amgr.sdk.fragment.AmgrSdkGatewayFragment;
import com.ticketmaster.amgr.sdk.objects.AmgrSdkEvent;
import com.ticketmaster.amgr.sdk.objects.TmIntents;
import com.ticketmaster.amgr.sdk.objects.TmServiceParams;

/**
 * Created by skateboard on 16-2-17.
 */
public class TicketActivity extends AppCompatActivity implements
        AmgrSdkGatewayFragment.OnAmgrSdkEventListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AmgrConfig config = new AmgrConfig();
        TmServiceParams serviceParams = new TmServiceParams();
        serviceParams.apiUrl = "myUrl";
        serviceParams.xClient = "myXClient";
        serviceParams.apiKey = "myApiKey";
        serviceParams.helpUrl = "myHelpUrl";
        try {
            AmgrGlobal.initializeEx(this, config, serviceParams);
        }
        catch(Exception ex) { }
        setContentView(R.layout.activity_ticket);

        AmgrSdkGatewayFragment amgrSdkFragment =
                AmgrSdkGatewayFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString(TmIntents.LAUNCH_PAGE, TmIntents.PAGE_NEXT_EVENT);
        amgrSdkFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.tm_container, amgrSdkFragment).commit();
    }

    @Override
    public void onAmgrSdkEvent(AmgrSdkEvent amgrSdkEvent) {

    }
}
