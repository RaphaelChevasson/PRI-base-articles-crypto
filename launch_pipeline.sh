env_name="base" # put yours here, default is "base"
anaconda_path="C:\anaconda3" # only for windows, put yours here



printf "\n---\n Searching for new publications\n---\n\n"
java -jar server-0.0.1-SNAPSHOT.jar


printf "\n---\n Classifying new publications\n---\n\n"

# activate python env
if [[ "$OSTYPE" == "msys" ]]; then # windows
	# find the _conda_activate command
	_CONDA_ROOT="$(cygpath $anaconda_path)"
	\. "$_CONDA_ROOT/etc/profile.d/conda.sh" || return $?
	# call it
	_conda_activate "$env_name"
else                               # linux
    source activate $env_name
fi

# run python script
python test.py

# deactivate python env
if [[ "$OSTYPE" == "msys" ]]; then # windows
	# find the _conda_deactivate command
	_CONDA_ROOT="$(cygpath $anaconda_path)"
	\. "$_CONDA_ROOT/etc/profile.d/conda.sh" || return $?
	# call it
	_conda_deactivate
else                               # linux
    source deactivate
fi


printf "\n---\n Done!\n---\n\n"
read -p "Press enter to close..."
