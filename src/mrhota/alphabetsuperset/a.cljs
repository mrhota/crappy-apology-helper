(ns mrhota.alphabetsuperset.a)

(defn get-el [id]
  (.getElementById js/document id))

(defn form-val [id]
  (-> id
      get-el
      .-value))

(defn get-style [id]
  (-> js/document
      (.getElementById id)
      .-style))

(defn set-display [id disp-style]
  (-> id
      get-style
      .-display
      (set! disp-style)))

(defn hide-all-questions []
  (set-display "question1" "none")
  (set-display "question2" "none")
  (set-display "question3" "none")
  (set-display "question4" "none"))

(defn ^:export nextQuestion [qid]
  (hide-all-questions)
  (set-display (str "question" qid) "block"))

(defn ^:export generateApology []
  (let [recipient (form-val "recipient")
        apologetic (= "yes" (form-val "apologetic"))
        feeling (form-val "feeling")
        reason (form-val "reason")
        apology (str "Dearest "
                     recipient
                     ",\n\nI am "
                     feeling
                     (if apologetic " that " " if ")
                     reason)]
    (hide-all-questions)
    (-> "apologyText"
        get-el
        .-value
        (set! apology))
    (set-display "result" "block")))
