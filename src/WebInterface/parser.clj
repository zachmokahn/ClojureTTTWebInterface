(ns WebInterface.parser
  (:require [WebInterface.constants :refer :all]
            [clojure.data.json :as json]))


(defn convert-turn [parameters]
  (if (= (get parameters TURN) PLAYER1) :player1 :player2))

(defn convert-mode [parameters]
  (if (= (get parameters MODE) PVC) :pvc :pvp))

(defn convert-difficulty [parameters]
  (if (= (get parameters DIFFICULTY) HARD) :hard :easy))

(defn convert-move [parameters]
  (get parameters MOVE))

(defn convert-board [parameters]
  (json/read-str (get parameters BOARD)))

(defn parse-new-game [game-parameters]
  (let [parameters (into {} game-parameters)]
  {:turn (convert-turn parameters)
   :mode (convert-mode parameters)
   :difficulty (convert-difficulty parameters)}))

(defn parse-active-game [game-parameters]
  (let [parameters (into {} game-parameters)]
  {:turn (convert-turn parameters)
   :mode (convert-mode parameters)
   :move (convert-move parameters)
   :board (convert-board parameters)
   :difficulty (convert-difficulty parameters)}))
