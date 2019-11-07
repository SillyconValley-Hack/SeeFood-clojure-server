(ns googlevision.core
  (:require [clojure.java.io :as io]
            [environ.core :refer [env]]
  )
  (:import [com.google.cloud.vision.v1 ImageAnnotatorClient]
           [com.google.cloud.vision.v1 Feature]
           [com.google.cloud.vision.v1 Image]
           [com.google.cloud.vision.v1 AnnotateImageRequest]
           [com.google.cloud.vision.v1 AnnotateImageResponse]
           [com.google.cloud.vision.v1 BatchAnnotateImagesResponse]
           [com.google.protobuf ByteString]
           [java.io FileInputStream]
  )

  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;
  ; (println (.exists (io/file "./public/Hot-Dogs.jpg" ))
  ; (println (env :GOOGLE_APPLICATION_CREDENTIALS)
  (let [client (ImageAnnotatorClient/create)
        photo-in-byte (ByteString/readFrom (new FileInputStream "./public/Hot-Dogs.jpg" ))
        image (.build (.setContent (Image/newBuilder) photo-in-byte))
        feature (.build (.setType (Feature/newBuilder) com.google.cloud.vision.v1.Feature$Type/LABEL_DETECTION))
        request (.addFeatures (AnnotateImageRequest/newBuilder) feature)
        request (.setImage request image)
        request (.build request)

        response (.batchAnnotateImages client [request])
        response (.getResponsesList response)
       ]
    (println response)
  )
)

