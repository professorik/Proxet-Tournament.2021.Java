package proxet.tournament.generator;

import proxet.tournament.generator.dto.Pair;
import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;
import proxet.tournament.generator.exceptions.NotEnoughPlayersException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TeamGenerator {

    private static final int vehicleTypes = 3;
    private static final int typePlayersNum = 3;

    public TeamGeneratorResult generateTeams(String filePath) {
        @SuppressWarnings("unchecked")
        Pair<String, Integer>[][] arr = new Pair[vehicleTypes][2 * typePlayersNum];
        for (Pair<String, Integer>[] row : arr)
            Arrays.fill(row, new Pair<>("", -1));
        try {
            Scanner in = new Scanner(new File(filePath));
            Pair<String, Integer> temp;
            while (in.hasNext()) {
                temp = new Pair<>(in.next(), in.nextInt());
                int vehicleType = in.nextInt() - 1;
                if (arr[vehicleType][0].getLast() < temp.getLast()) {
                    int i;
                    for (i = 1; i < arr[vehicleType].length && arr[vehicleType][i].getLast() < temp.getLast(); i++) {
                        arr[vehicleType][i - 1] = arr[vehicleType][i];
                    }
                    arr[vehicleType][i - 1] = temp;
                }
            }
            for (int i = 0; i < 3; i++) {
                if (arr[i][0].getLast() == -1)
                    throw new NotEnoughPlayersException();
            }
        } catch (FileNotFoundException | NotEnoughPlayersException e) {
            e.printStackTrace();
        }
        List<Player> firstTeam = new ArrayList<>();
        List<Player> secondTeam = new ArrayList<>();
        for (int i = 0; i < vehicleTypes; i++) {
            for (int j = 0; j < typePlayersNum; j++) {
                firstTeam.add(new Player(arr[i][j].getFirst(), i + 1));
                secondTeam.add(new Player(arr[i][j + typePlayersNum].getFirst(), i + 1));
            }
        }
        return new TeamGeneratorResult(firstTeam, secondTeam);
    }
}
