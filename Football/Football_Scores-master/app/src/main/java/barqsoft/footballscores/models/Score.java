package barqsoft.footballscores.models;

/**
 * Created by ctolosa on 04/01/2016.
 */
public class Score {

    private String date;

    private Integer time;

    private String home;

    private String away;

    private Integer league;

    private String home_goals;

    private String away_goals;

    private Integer match_id;

    private Integer match_day;

    private String final_score;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public Integer getLeague() {
        return league;
    }

    public void setLeague(Integer league) {
        this.league = league;
    }

    public String getHome_goals() {
        return home_goals;
    }

    public void setHome_goals(String home_goals) {
        this.home_goals = home_goals;
    }

    public String getAway_goals() {
        return away_goals;
    }

    public void setAway_goals(String away_goals) {
        this.away_goals = away_goals;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public Integer getMatch_day() {
        return match_day;
    }

    public void setMatch_day(Integer match_day) {
        this.match_day = match_day;
    }

    public String getFinal_score() {
        return final_score;
    }

    public void setFinal_score(String final_score) {
        this.final_score = final_score;
    }
}
