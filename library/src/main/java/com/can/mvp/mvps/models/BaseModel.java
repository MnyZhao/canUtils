package com.can.mvp.mvps.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.can.mvp.R;
import com.can.mvp.base.mvp.IBaseModel;
import com.can.mvp.bean.requestBean.BaseRequestBean;
import com.can.mvp.bean.responseBean.User;
import com.can.mvp.utils.BitmapUtils;
import com.can.mvp.utils.QRCodeUtils;
import com.can.mvp.utils.StringUtils;
import com.google.zxing.WriterException;

/**
 * Created by can on 2018/4/4.
 */

public class BaseModel {

    //请求网络数据
    public void getData(BaseRequestBean baseRequestBean, IBaseModel.onGetDataFinishedListener listener){
        
    }

    //获取二维码
    public void getQRCode(Context context,String content, Bitmap bitmap, IBaseModel.onQRCodeListener listener){
        if(StringUtils.isEmpty(content)&&bitmap==null)
            listener.onDataError("内容不能为空");
        else
            try {
                if(bitmap==null)
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_app_logo);
                if(bitmap==null)
                    listener.onSuccess(QRCodeUtils.generateStringBitmap(content,400,400));
                else
                    listener.onSuccess(QRCodeUtils.generateLogoBitmap(content,bitmap));
            } catch (WriterException e) {
                e.printStackTrace();
            }
    }

    //保存图片
    public void saveImageToGallery(Context context,Bitmap bitmap){
        BitmapUtils.saveImageToGallery(context,bitmap);
    }

    //登录返回
    public void login(final String username, final String password, final IBaseModel.onLoginFinishedListener listener) {
        if(StringUtils.isEmpty(username)){
            listener.onUsernameError();
            return;
        }
        if(StringUtils.isEmpty(password)){
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }

    //请求登录
    public void onUser(String userName, String userPassword, IBaseModel.onGetUserFinishedListener listener) {
        if(StringUtils.isEmpty(userName)){
            listener.onError();
            return;
        }
        if(StringUtils.isEmpty(userPassword)){
            listener.onError();
            return;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserId(userPassword);
        listener.onSuccess(user);
    }



}
