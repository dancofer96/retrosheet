import os
import json

ORIGINAL_DATA_PATH = '../data/original'
MODIFIED_DATA_PATH = '../data/modified'
COLUMNS_FILENAME = './column_headers.json'

def main():
	for original_filename in os.listdir(ORIGINAL_DATA_PATH):
		print('ADDING COLUMN HEADERS TO ' + original_filename)
		
		modified_filename = MODIFIED_DATA_PATH + '/' + original_filename
		original_filename = ORIGINAL_DATA_PATH + '/' + original_filename
		
		with open(COLUMNS_FILENAME, 'r') as columns_file:
			columns_data = json.load(columns_file)
			game_columns = columns_data['game_columns']
		
		with open(original_filename, 'r') as original_file, open(modified_filename, 'w') as modified_file:
			modified_file.write(','.join(game_columns.values()) + '\n')
			for line in original_file.readlines():
				modified_file.write(getRowFromColumnSubset(line.split(','), game_columns.keys()))
			
				
def getRowFromColumnSubset(row_values, column_indices):
	values_subset = []
	for index in column_indices: values_subset.append(row_values[int(index)].strip('"'))
	return ','.join(values_subset) + '\n'
	

if __name__ == '__main__':
	main()