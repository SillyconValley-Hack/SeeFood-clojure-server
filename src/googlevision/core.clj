(ns googlevision.core
  (:require [clojure.java.io :as io]
            )

  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;
  (println (.exists (io/file "./public/Hot-Dogs.jpg" ))
           )
)

