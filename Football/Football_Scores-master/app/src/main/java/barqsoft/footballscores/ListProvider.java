package barqsoft.footballscores;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import barqsoft.footballscores.models.Score;

/**
 * Created by ctolosa on 04/01/2016.
 */
public class ListProvider implements RemoteViewsService.RemoteViewsFactory {

    private ScoresDBHelper scoresDBHelper;

    private List<Score> scores = new ArrayList<Score>();

    Context mContext = null;

    public ListProvider(Context context, Intent intent) {
        mContext = context;
        scoresDBHelper = new ScoresDBHelper(context);
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(), R.layout.scores_list_item_football_widget);

        mView.setImageViewResource(R.id.home_crest_widget, Utilies.getTeamCrestByTeamName(scores.get(position).getHome()));
        mView.setTextViewText(R.id.home_name_widget, scores.get(position).getHome());
        mView.setTextColor(R.id.home_name_widget, Color.BLACK);

        mView.setImageViewResource(R.id.away_crest_widget, Utilies.getTeamCrestByTeamName(scores.get(position).getAway()));
        mView.setTextViewText(R.id.away_name_widget, scores.get(position).getAway());
        mView.setTextColor(R.id.away_name_widget, Color.BLACK);

        mView.setTextViewText(R.id.score_textview_widget, scores.get(position).getFinal_score());
        mView.setTextColor(R.id.score_textview_widget, Color.BLACK);

        mView.setTextViewText(R.id.data_textview_widget, scores.get(position).getDate());
        mView.setTextColor(R.id.data_textview_widget, Color.BLACK);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra(FootballWidget.EXTRA_LIST_VIEW_ROW_NUMBER, position);
        mView.setOnClickFillInIntent(R.id.rl_widget, fillInIntent);

        return mView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    private void initData() {
        scores.clear();

        boolean existScore = false;

        SQLiteDatabase db = scoresDBHelper.getReadableDatabase();
        if (db == null) {
            existScore = false;
        }

        String query = "SELECT * FROM scores_table";
        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setDate(cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.DATE_COL)));
                score.setTime(cursor.getInt(cursor.getColumnIndex(DatabaseContract.scores_table.TIME_COL)));
                score.setHome(cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_COL)));
                score.setAway(cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_COL)));
                score.setLeague(cursor.getInt(cursor.getColumnIndex(DatabaseContract.scores_table.LEAGUE_COL)));

                String home_goals = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL));
                if (home_goals != null && home_goals.equalsIgnoreCase("-1")) {
                    score.setHome_goals("-");
                } else {
                    score.setHome_goals(home_goals);
                }

                String away_goals = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_GOALS_COL));
                if (away_goals != null && away_goals.equalsIgnoreCase("-1")) {
                    score.setAway_goals("-");
                } else {
                    score.setAway_goals(away_goals);
                }

                score.setMatch_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.scores_table.MATCH_ID)));
                score.setMatch_day(cursor.getInt(cursor.getColumnIndex(DatabaseContract.scores_table.MATCH_DAY)));
                if ((score.getHome_goals() != null) && (score.getAway_goals() != null)) {
                    if (!score.getHome_goals().equalsIgnoreCase("") && !score.getAway_goals().equalsIgnoreCase("")) {
                        score.setFinal_score(score.getHome_goals().concat(" - ").concat(score.getAway_goals()));
                        scores.add(score);
                    }
                }

            } while (cursor.moveToNext());
        }
    }

    @Override
    public void onDestroy() {

    }

}