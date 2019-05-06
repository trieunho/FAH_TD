package com.example.fah.FAHScreen.Main.GridView.Menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.CommonUtils.ImageUtils;
import com.example.fah.FAHCommon.FAHExcuteData.ExcuteString;
import com.example.fah.R;

import java.util.List;

public class GridListMenuMainAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Menu> listData;
    private String permissionUser;

    public GridListMenuMainAdapter(Context context, List<Menu> listData, String permissionUser) {
        this.context = context;
        this.listData = listData;
        this.permissionUser = permissionUser;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_main_menu, null);
            holder = new ViewHolder();
            holder.layoutMenu = convertView.findViewById(R.id.layoutMenu);
            holder.tvNameFunc = convertView.findViewById(R.id.tvNameFunc);
            holder.ivFunction = convertView.findViewById(R.id.ivFunction);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Menu objMenu = this.listData.get(position);

        if(checkPermissonUser(objMenu.getPermission())){
            holder.layoutMenu.setVisibility(View.VISIBLE);
            holder.tvNameFunc.setText(objMenu.getName());
            int imageId = this.getMipmapResIdByName(objMenu.getImage());
            holder.ivFunction.setImageResource(imageId);
//        if(objMenu.isUserMenu()==true)
//        {
//            if(objMenu.getImage()!=null && objMenu.getImage()!=""){
//
//                holder.ivFunction.setImageDrawable(ImageUtils.roundedImage(context, getImageAvatar(objMenu.getImage())));
//               // int imageId = this.getMipmapResIdByName("ic_launcher_default_avata");
//               // holder.ivFunction.setImageResource(imageId);
//            }else{
//                int imageId = this.getMipmapResIdByName("ic_launcher_default_avata");
//                holder.ivFunction.setImageResource(imageId);
//            }
//
//        }
//        else
//         {
//             int imageId = this.getMipmapResIdByName(objMenu.getImage());
//             holder.ivFunction.setImageResource(imageId);
//         }
        }else{
            holder.layoutMenu.setVisibility(View.INVISIBLE);
        }



        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        LinearLayout layoutMenu;
        TextView tvNameFunc;
        ImageView ivFunction;
    }

    private Bitmap getImageAvatar(String imgBase64) {
        Bitmap src = null;
        try {
            byte[] decodedString = Base64.decode(imgBase64, Base64.DEFAULT);
            src = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (Exception e) {
            Toast.makeText(context, "Lỗi khi setimage", Toast.LENGTH_SHORT).show();
        }
        return src;
    }

    private boolean checkPermissonUser(String permission){
//        if(ExcuteString.IsNullOrEmpty(permission)){
//            return false;
//        }
//
//        if(permission.indexOf(this.permissionUser) == -1){
//            return false;
//        }

        return true;
    }
}
