package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseMap;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, 5.82005, 4.6494, 10.51)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(7, 65, Math.toRadians(-90)))
                .strafeTo(new Vector2d(55, 55))
                //lift here
                //.addTemporalMarker(() -> grabber.grabberOpen())
                //lift down
                .waitSeconds(0.5)

                //sample dropped into bucket (2)

               // .splineTo(new Vector2d(40, 25), Math.toRadians(0))
                .strafeTo(new Vector2d(40, 25))
                /*.addTemporalMarker(() -> ostrich.ostrichDown())
                .addTemporalMarker(() -> grabber.grabberClose())
                .addTemporalMarker(() -> ostrich.ostrichUp()) */
                .waitSeconds(0.5)

                //picked up new sample (3)

                .strafeTo(new Vector2d(55, 55))
                //lift here
                //.addTemporalMarker(() -> grabber.grabberOpen())
                //lift down
                .waitSeconds(0.5)

                //sample dropped into bucket (3)

                .strafeTo(new Vector2d(55, 25))
                /*.addTemporalMarker(() -> ostrich.ostrichDown())
                .addTemporalMarker(() -> grabber.grabberClose())
                .addTemporalMarker(() -> ostrich.ostrichUp()) */
                .waitSeconds(0.5)

                //picked up new sample (4)

                .strafeTo(new Vector2d(55, 55))
                //lift here
                //.addTemporalMarker(() -> grabber.grabberOpen())
                //lift down
                .waitSeconds(0.5)

                //dropped sample into bucket (4)

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}