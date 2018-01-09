package ballot.corentin.mapsui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class PhotoInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private View infoWindowView;

    PhotoInfoWindowAdapter(Activity parent) {
        infoWindowView = parent.getLayoutInflater().inflate(R.layout.info_window, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        render(marker, infoWindowView);
        return infoWindowView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        render(marker, infoWindowView);
        return infoWindowView;
    }

    private void render(Marker marker, View view) {
        ImageView iv = view.findViewById(R.id.picture);
        iv.setImageResource((int) marker.getTag());
        //iv.setImageResource(R.drawable.rome);

        TextView title = view.findViewById(R.id.title);
        title.setText(marker.getTitle());

        TextView snippet = view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());

    }
}
