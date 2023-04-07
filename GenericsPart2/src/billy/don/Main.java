package billy.don;
public class Main {
    public static void main(String[] args) {
        FootBallPlayer samuel = new FootBallPlayer("Samuel");
        BaseBallPlayer pat = new BaseBallPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team<FootBallPlayer> adelaideCrows = new Team<>("Adelaide Crows");

        adelaideCrows.addPlayer(samuel);
//        adelaideCrows.addPlayer(pat);
//        adelaideCrows.addPlayer(beckham);

        System.out.println(adelaideCrows.numPlayers());

        Team<BaseBallPlayer> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);
        System.out.println(baseballTeam.numPlayers());

        Team<SoccerPlayer> brokenTeam = new Team<>("This will work");
        brokenTeam.addPlayer(beckham);
        System.out.println(brokenTeam.numPlayers());

    }
}