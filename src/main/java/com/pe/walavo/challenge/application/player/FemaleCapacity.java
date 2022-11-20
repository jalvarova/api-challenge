package com.pe.walavo.challenge.application.player;

import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FemaleCapacity implements CapacityPlayer {

    private PlayerDTO player;

    @Override
    public void measureCapacity() {
        player.setSkill(((player.getSkill() +
                player.getReactionTime()) / 2) / RandomUtils.nextInt(1, 10));
    }
}
