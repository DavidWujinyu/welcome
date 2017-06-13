package net.john.activity;

import java.util.Map;

import net.john.R;
import net.john.data.User;
import net.john.util.HttpConnectUtil;
import net.john.util.RTMPConnectionUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smaxe.uv.Responder;
import com.smaxe.uv.client.INetConnection;
import com.smaxe.uv.client.License;
import com.smaxe.uv.client.NetConnection;

public class ChoiceActivity extends Activity {
	
	private Button button_video;
	private Button button_upload;
	private Button button_logout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_choice);
		
		button_video = (Button)this.findViewById(R.id.button_video);
		button_upload = (Button)this.findViewById(R.id.button_upload);
		button_logout = (Button)this.findViewById(R.id.button_logout);
		
		new Thread() {
			public void run () {
				RTMPConnectionUtil.ConnectRed5(ChoiceActivity.this);
			}
		}.start();
		
		button_video.setOnClickListener(new View.OnClickListener() {
			

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChoiceActivity.this, VideoActivity.class);
				startActivity(intent);
			}
		});
		
		button_upload.setOnClickListener(new View.OnClickListener() {
			

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChoiceActivity.this, ShootActivity.class);
				startActivity(intent);
			}
		});
		
		button_logout.setOnClickListener(new View.OnClickListener() {
			

			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences remember = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = remember.edit();
				editor.putInt("id", -1);
				editor.putString("phone", null);
				editor.putString("name", null);
				editor.commit();
				User.id = -1;
				User.phone = null;
				User.realname = null;
				RTMPConnectionUtil.connection.close();
				finish();
			}
		});
	}
}
