package com.pe.walavo.challenge.business.player;

import com.pe.walavo.challenge.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FemaleCapacity implements CapacityPlayer {

    private PlayerDTO player;

    @Override
    public void measureCapacity() {
        player.setSkill((player.getSkill() + player.getReactionTime()) / 2);
    }
}
