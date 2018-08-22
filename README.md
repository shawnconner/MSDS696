# MSDS692 - PII Clustering

## Project Description
During my last Data Science Data Science Practicum I created models to try to identify individuals that are the same person. I created two models to compare 2 inviduals to deteremine if they are the same person.  The first model was a random forest and the second model was a Logistic Regression.  The output of these models was the id of the two individuals compared and a flag indicating whether the model identified them as the same person.  

For this project I'm going to take the output of the models and create graphs of the individuals.  The goal is to create clusters or indiviudals that are the same person.  The reason for using a graph is that all the individuals may not have been linked together.  Some individuals in the cluster might have been linked using another link in the graph.


## Data ##
 * The input to the models was around 20 billion compbonations of 2 individuals.  The output of the model was the same size. 
 * I took that output and removed any records where the models did not indciate the individual was the same person. The universe was around 7.5 billion people.  THis is the input I will use into the graph.
 * I also had to create a file that had all the distinct indiviudals in it.  That file was around 2 billion people.

## Data Analysis
 * For the graph I needed to make sure that each combonation of 2 individuals was only in the file once.  I did that by putting the minimum id in the first position and then did a distinct on the Universe.  The number of distinct values matched the input.  Based on this my universe was already distinct.
 
 * I wanted to get a sense of if there were any ids that mapped to a high number of other individuals.  To look at this I did a group by on the first individual id and got a count of the number of individuals each cluster had.  I then created a bar graph in Tableau show the distribution.
 
 * I also wanted to get a sense of the max number of clusters to expect. To get this estimate I looked at the number of distinct individuals in the first position of model output.  I expected the number of unique individuals in my graph to be X based on that.
 
  * The last thing I did was check to see how many of the individuals in the first column appeard in the second column.  This gave me a sense of how many clusters would expand out beyond the initial grouping.

## Data Cleansing and Preperation

Most of the cleansing and preperation was done before running the models in the previous project.  For the project I wanted to remove individuls that linked to more then 10 other ids.  I picked this number based on a few reasons
 * On average I expected there to be around 8 to 10 indiviudals linked to a together.  These clusters are well above that number.
 * When I looked at these groups they didn't like the same person.
 * I wanted to be very conserative in my groupings.
 
## GraphX

To create graphs I used GraphX in Spark.  I used Scala as the programming language for all my Spark jobs.  The firest input into GraphX was a file that contained all the ids assigned to individuals.  This file was used to create the Vertexes.  The second input was the output of my Random Forest model. This file had pairs of ids that were identified as the same person.  This file was used as the edges.  The output of the file was a csv which had the minimim id for each cluster and then a pipe delimited list of all the ids in the cluster.  The mimiumum id is what I will use to identify clusters.  

## Future work

The following items are things I would like to do in the future
 * For this analysis I only used the output of one of my models. I would like to try to combine the output of multiple models to come up with a confidence score for each link.  
 * For the models I only created clusters based on name and address.  I would like to also try clusters based on email address/phone and first name and last name.  
 * I would like to add a confidence value to each edge to allow me to pull out links at a certain confidence level.

## Resources
 * US Census Data - Population by State - https://www.census.gov/data/tables/2017/demo/popest/state-total.html#par_textimage_1574439295
 * US Census Quick Facts - https://www.census.gov/quickfacts/fact/table/US/PST045217
 * Example of linking records - https://towardsdatascience.com/record-linking-with-apache-sparks-mllib-graphx-d118c5f31f83
