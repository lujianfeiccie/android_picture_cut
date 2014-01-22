package com.lujianfei.piccut;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PictureCutDemoAcitivityActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private ImageView imgView,imgView2;
	private Button button;
	Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        
        button.setOnClickListener(this);
    }
    
    private void initView(){
    	imgView = (ImageView)findViewById(R.id.imgView);
    	imgView2 = (ImageView)findViewById(R.id.imgView2);
    	button = (Button)findViewById(R.id.button);
    	 bitmap = drawableToBitmap(getResources().getDrawable(R.drawable.psu));
    	imgView.setImageBitmap(bitmap);
    }
    /**
	 * 将Drawable转化为Bitmap
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable){ 
	int width = drawable.getIntrinsicWidth(); 
	int height = drawable.getIntrinsicHeight(); 
	Bitmap bitmap = Bitmap.createBitmap(width, height, 
	drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 
	: Bitmap.Config.RGB_565); 
	Canvas canvas = new Canvas(bitmap); 
	drawable.setBounds(0,0,width,height); 
	drawable.draw(canvas); 
	return bitmap;

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			 bitmap = toRoundBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight()/2);
			 imgView2.setImageBitmap(bitmap);
			break;
		default:
			break;
		}
	}
    /**
     * 转换图片成圆�?     * @param bitmap 传入Bitmap对象
     * @return
     */
	public Bitmap toRoundBitmap(Bitmap bitmap,int x,int y,int width,int height) {
		float left=x, top=y, right=left+width, bottom=top+height;
		float dst_left=left, dst_top=top, dst_right=dst_left+right, dst_bottom=dst_top+bottom;
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		Rect src = new Rect((int) left, (int) top, (int) right,(int) bottom);
		Rect dst = new Rect((int) dst_left, (int) dst_top,(int) dst_right, (int) dst_bottom);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}
}