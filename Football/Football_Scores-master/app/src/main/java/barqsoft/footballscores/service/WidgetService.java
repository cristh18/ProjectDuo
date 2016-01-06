package barqsoft.footballscores.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.ListProvider;

/**
 * Created by ctolosa on 04/01/2016.
 */
public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        ListProvider dataProvider = new ListProvider(
                getApplicationContext(), intent);
        return dataProvider;
    }
}
