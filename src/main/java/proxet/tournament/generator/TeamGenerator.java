package proxet.tournament.generator;

import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TeamGenerator {

    private class Pair<T, K> {
        T first;
        K last;

        public Pair(T first, K last) {
            this.first = first;
            this.last = last;
        }
    }

    public TeamGeneratorResult generateTeams(String filePath) {
        Pair<String, Integer>[][] arr = new Pair[3][6];
        for (Pair<String, Integer>[] row : arr)
            Arrays.fill(row, new Pair<>("", -1));
        try {
            Scanner in = new Scanner(new File(filePath));
            while (in.hasNextLine()) {
                Pair<String, Integer> temp = new Pair<>(in.next(), in.nextInt());
                int carType = in.nextInt() - 1;
                if (arr[carType][0].last < temp.last) {
                    int i;
                    for (i = 1; i < arr[carType].length && arr[carType][i].last < temp.last; i++) {
                        arr[carType][i - 1] = arr[carType][i];
                    }
                    arr[carType][i - 1] = temp;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Player> firstTeam = new ArrayList<>();
        List<Player> secondTeam = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                firstTeam.add(new Player(arr[i][j].first, i + 1));
                secondTeam.add(new Player(arr[i][j+3].first, i + 1));
            }
        }
        return new TeamGeneratorResult(firstTeam, secondTeam);
    }
}
