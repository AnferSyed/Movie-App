package sample.com.movieapp.util.databinding.components;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingComponent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sample.com.webservice.common.service.data.ServiceNames;

/**
 * Created by anfer on 25-Sep-18.
 */

public class ViewDataBindingComponent implements DataBindingComponent {

    @BindingAdapter(value = {"app:imageUrl"})
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(ServiceNames.IMAGE_BASE_URL + imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    @BindingAdapter(value = {"app:release", "app:lang"})
    public static void setReleaseAndLang(TextView textView, String release, String lang) {
        textView.setText(release.substring(0, 4)
                + " | "
                + lang.toUpperCase()
        );
    }
}
