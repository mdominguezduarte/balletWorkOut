package com.example.balletworkout

class Constants {
    companion object {

        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()

            val ballet2 =
                ExerciseModel(1, "Ejercicio 1", R.drawable.ballet2, false, false)
            exerciseList.add(ballet2)

            val ballet3 = ExerciseModel(2, "Ejercicio 2", R.drawable.ballet3, false, false)
            exerciseList.add(ballet3)

            val ballet4 = ExerciseModel(3, "Ejercicio 3", R.drawable.ballet4, false, false)
            exerciseList.add(ballet4)

            val ballet5 =
                ExerciseModel(4, "Ejercicio 4", R.drawable.ballet5, false, false)
            exerciseList.add(ballet5)

            val ballet6 =
                ExerciseModel(
                    5,
                    "Ejercicio 5",
                    R.drawable.ballet6,
                    false,
                    false
                )
            exerciseList.add(ballet6)

            val ballet7 = ExerciseModel(6, "Ejercicio 6", R.drawable.ballet7, false, false)
            exerciseList.add(ballet7)

            val ballet8 =
                ExerciseModel(
                    7,
                    "Ejercicio 7",
                    R.drawable.ballet8,
                    false,
                    false
                )
            exerciseList.add(ballet8)

            return exerciseList
        }
    }
}