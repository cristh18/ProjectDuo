package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import barqsoft.footballscores.service.WidgetService;

/**
 * Created by ctolosa on 30/12/2015.
 */
public class FootballWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,
                FootballWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for (int i : allWidgetIds) {
            RemoteViews mView = initViews(context, appWidgetManager, i);
            appWidgetManager.updateAppWidget(i, mView);
        }
    }

    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId) {

        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.football_widget);

        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        mView.setRemoteAdapter(widgetId, R.id.listViewWidget, intent);

        Intent openApp = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openApp, 0);
        //mView.setOnClickPendingIntent(R.id.buttonOpenApp, pendingIntent);
        mView.setPendingIntentTemplate(R.id.listViewWidget, pendingIntent);

        return mView;
    }

    public static final String EXTRA_LIST_VIEW_ROW_NUMBER = "mypackage.EXTRA_LIST_VIEW_ROW_NUMBER";

}
