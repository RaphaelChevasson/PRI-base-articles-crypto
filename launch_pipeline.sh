printf "\n---\n Searching for new publications\n---\n\n"
java -jar server-0.0.1-SNAPSHOT.jar
printf "\n---\n Classifying new publications\n---\n\n"
#todo: call activate base
python test.py
#todo: call deactivate
printf "\n---\n Done!\n---\n\n"
read -p "Press enter to close..."